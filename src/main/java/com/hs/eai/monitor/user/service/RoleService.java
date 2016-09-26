package com.hs.eai.monitor.user.service;

import com.hs.eai.monitor.user.model.Role;

public interface RoleService {

	Role findRoleByName(String roleName);
}
