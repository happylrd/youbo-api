package io.happylrd.youbo.service.impl;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Org;
import io.happylrd.youbo.model.domain.OrgMember;
import io.happylrd.youbo.repository.OrgMemberRepository;
import io.happylrd.youbo.repository.OrgRepository;
import io.happylrd.youbo.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgRepository orgRepository;

    @Autowired
    private OrgMemberRepository orgMemberRepository;

    @Override
    public ServerResponse<List<Org>> listOrg() {
        return ServerResponse.createBySuccess(orgRepository.findAll());
    }

    @Override
    public ServerResponse<Org> getOrg(Long orgId) {
        return ServerResponse.createBySuccess(orgRepository.findOne(orgId));
    }

    @Override
    public ServerResponse<List<OrgMember>> listMember(Long orgId) {
        return ServerResponse.createBySuccess(orgMemberRepository.findByGroupId(orgId));
    }
}
