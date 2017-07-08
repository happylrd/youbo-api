package io.happylrd.youbo.controller;

import com.google.common.collect.Maps;
import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.*;
import io.happylrd.youbo.model.dto.OrgDTO;
import io.happylrd.youbo.model.dto.TweetFragmentDTO;
import io.happylrd.youbo.model.dto.UserDTO;
import io.happylrd.youbo.model.vo.LoginVO;
import io.happylrd.youbo.model.vo.RegisterVO;
import io.happylrd.youbo.model.vo.UserInfoVO;
import io.happylrd.youbo.service.FileService;
import io.happylrd.youbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${ftp.server.http-prefix}")
    private String imageServerPrefix;

    @PostMapping(value = "/register")
    private ServerResponse<UserDTO> register(RegisterVO registerVO) {
        return userService.register(registerVO);
    }

    @PostMapping(value = "/login")
    private ServerResponse<UserDTO> login(LoginVO loginVO) {
        return userService.login(loginVO);
    }

    @GetMapping(value = "/{id}")
    private ServerResponse<UserDTO> getInfo(@PathVariable("id") Long id) {
        return userService.getInfo(id);
    }

    @PutMapping("/{id}")
    private ServerResponse<UserInfoVO> updateInfo(@PathVariable("id") Long id,
                                                  @RequestBody UserInfoVO userInfoVO) {
        return userService.updateInfo(id, userInfoVO);
    }

    @PostMapping("/{id}/tweets")
    private ServerResponse<Tweet> publishTweet(@PathVariable("id") Long id,
                                               @RequestBody List<TweetFragmentDTO> fragmentDTOs) {
        return userService.publishTweet(id, fragmentDTOs);
    }

    @GetMapping("/{id}/tweets")
    private ServerResponse<List<Tweet>> listMyTweet(@PathVariable("id") Long id) {
        return userService.listMyTweet(id);
    }

    // TODO: content style of sending and receiving will be improved later
    @PostMapping("/{id}/tweets/{tweetId}/comments")
    private ServerResponse<Comment> publishComment(@PathVariable("id") Long id,
                                                   @PathVariable("tweetId") Long tweetId,
                                                   @RequestParam("content") String content) {
        return userService.publishComment(id, tweetId, content);
    }

    @GetMapping("/{id}/comments")
    private ServerResponse<List<Comment>> listMyComment(@PathVariable("id") Long id) {
        return userService.listMyComment(id);
    }

    @PostMapping("/{id}/tweets/{tweetId}/collections")
    private ServerResponse<Collection> collectTweet(@PathVariable("id") Long id,
                                                    @PathVariable("tweetId") Long tweetId) {
        return userService.collectTweet(id, tweetId);
    }

    @GetMapping("/{id}/collections")
    private ServerResponse<List<Collection>> listMyCollection(@PathVariable("id") Long id) {
        return userService.listMyCollection(id);
    }

    @PutMapping("/{id}/collections/{collectionId}")
    private ServerResponse<Collection> cancelCollection(@PathVariable("id") Long id,
                                                        @PathVariable("collectionId") Long collectionId) {
        return userService.cancelCollection(collectionId);
    }

    @PostMapping("/{id}/tweets/{tweetId}/favorites")
    private ServerResponse<Favorite> doFavorite(@PathVariable("id") Long id,
                                                @PathVariable("tweetId") Long tweetId) {
        return userService.doFavorite(id, tweetId);
    }

    @GetMapping("/{id}/favorites")
    private ServerResponse<List<Favorite>> listMyFavorite(@PathVariable("id") Long id) {
        return userService.listMyFavorite(id);
    }

    @PutMapping("/{id}/favorites/{favoriteId}")
    private ServerResponse<Favorite> cancelFavorite(@PathVariable("id") Long id,
                                                    @PathVariable("favoriteId") Long favoriteId) {
        return userService.cancelFavorite(favoriteId);
    }

    @PostMapping("/{id}/orgs")
    private ServerResponse<Org> createOrg(@PathVariable("id") Long id,
                                          @RequestBody OrgDTO orgDTO) {
        return userService.createOrg(id, orgDTO);
    }

    @GetMapping("/{id}/orgs")
    private ServerResponse<List<Org>> listMyOrg(@PathVariable("id") Long id) {
        return userService.listMyOrg(id);
    }

    @PutMapping("/{id}/avatar")
    private ServerResponse updateAvatar(@PathVariable("id") Long id,
                                        @RequestParam(value = "upload_avatar", required = false) MultipartFile file,
                                        HttpServletRequest request) {
        String path = request.getSession().getServletContext()
                .getRealPath("upload");
        String targetFileName = fileService.upload(file, path);

        String url = imageServerPrefix + targetFileName;

        return userService.updateAvatar(id, url);
    }

    /**
     * for all file type to upload, will make a extra function for implementing later
     * ignore the route now
     */
    @PostMapping(value = "/upload")
    public ServerResponse upload(@RequestParam(value = "upload_file", required = false) MultipartFile file,
                                 HttpServletRequest request) {
        String path = request.getSession().getServletContext()
                .getRealPath("upload");
        String targetFileName = fileService.upload(file, path);

        String url = "http://assets.youbo.io/" + targetFileName;
        Map<String, String> fileMap = Maps.newHashMap();
        fileMap.put("fileName", targetFileName);
        fileMap.put("url", url);

        return ServerResponse.createBySuccess("上传文件成功",
                fileMap);
    }
}
