package io.happylrd.youbo.service;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Org;

import java.util.List;

public interface OrgService {

    ServerResponse<List<Org>> listOrg();

    ServerResponse<Org> getOrg(Long orgId);
}
