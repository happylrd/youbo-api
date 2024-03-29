package io.happylrd.youbo.controller;

import com.google.common.collect.Maps;
import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.*;
import io.happylrd.youbo.model.dto.OrgDTO;
import io.happylrd.youbo.model.dto.TweetDTO;
import io.happylrd.youbo.model.dto.TweetFragmentDTO;
import io.happylrd.youbo.model.dto.UserDTO;
import io.happylrd.youbo.model.vo.*;
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
    private ServerResponse<UserDTO> register(@RequestBody RegisterVO registerVO) {
        return userService.register(registerVO);
    }

    @PostMapping(value = "/login")
    private ServerResponse<UserDTO> login(LoginVO loginVO) {
        return userService.login(loginVO);
    }

    @GetMapping(value = "/{username}")
    private ServerResponse<UserDTO> getInfo(@PathVariable("username") String  username) {
        return userService.getInfo(username);
    }

    @GetMapping("/id/{id}")
    private ServerResponse<UserVO> getInfoById(@PathVariable("id") Long id) {
        return userService.getInfoById(id);
    }

    @GetMapping("/{id}/info")
    private ServerResponse<UserInfoVO> getNormalInfo(@PathVariable("id") Long id) {
        return userService.getNormalInfo(id);
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
    private ServerResponse<List<TweetDTO>> listMyTweet(@PathVariable("id") Long id) {
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
    private ServerResponse<List<CommentVO>> listMyComment(@PathVariable("id") Long id) {
        return userService.listMyComment(id);
    }

    @PostMapping("/{id}/tweets/{tweetId}/collections")
    private ServerResponse<Collection> collectTweet(@PathVariable("id") Long id,
                                                    @PathVariable("tweetId") Long tweetId) {
        return userService.collectTweet(id, tweetId);
    }

    @GetMapping("/{id}/collections")
    private ServerResponse<List<TweetDTO>> listMyCollection(@PathVariable("id") Long id) {
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
    private ServerResponse<List<TweetDTO>> listMyFavorite(@PathVariable("id") Long id) {
        return userService.listMyFavorite(id);
    }

    @PutMapping("/{id}/favorites/{favoriteId}")
    private ServerResponse<Favorite> cancelFavorite(@PathVariable("id") Long id,
                                                    @PathVariable("favoriteId") Long favoriteId) {
        return userService.cancelFavorite(favoriteId);
    }

    @PostMapping("/{id}/following/{targetId}")
    private ServerResponse<UserFollow> doFollowing(@PathVariable("id") Long id,
                                                   @PathVariable("targetId") Long targetId) {
        return userService.doFollowing(id, targetId);
    }

    @GetMapping("/{id}/following")
    private ServerResponse<List<FollowVO>> listMyFollowing(@PathVariable("id") Long id) {
        return userService.listMyFollowing(id);
    }

    @GetMapping("/{id}/followers")
    private ServerResponse<List<FollowVO>> listMyFollower(@PathVariable("id") Long id) {
        return userService.listMyFollower(id);
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

    /**
     * need to be improved later
     */
    @PostMapping("/{ownerId}/orgs/{orgId}/members/{userId}")
    private ServerResponse<OrgMember> addMemberToOrg(@PathVariable("ownerId") Long ownerId,
                                                     @PathVariable("orgId") Long orgId,
                                                     @PathVariable("userId") Long userId) {
        return userService.addMemberToOrg(ownerId, orgId, userId);
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

        String url = "http://image.youbo.io/" + targetFileName;
        Map<String, String> fileMap = Maps.newHashMap();
        fileMap.put("fileName", targetFileName);
        fileMap.put("url", url);

        return ServerResponse.createBySuccess("上传文件成功",
                fileMap);
    }
}
