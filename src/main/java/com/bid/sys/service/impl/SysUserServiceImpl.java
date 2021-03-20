package com.bid.sys.service.impl;

import com.bid.common.exception.ServiceException;
import com.bid.common.utils.DateUtils;
import com.bid.common.utils.PageHelperUtils;
import com.bid.common.utils.StringUtils;
import com.bid.common.utils.security.Digests;
import com.bid.common.utils.security.Encodes;
import com.bid.common.web.PageInfo;
import com.bid.common.web.service.BaseServiceImpl;
import com.bid.sys.mapper.SysUserMapper;
import com.bid.sys.mapper.SysUserRoleMapper;
import com.bid.sys.model.SysUser;
import com.bid.sys.model.SysUserRole;
import com.bid.sys.model.result.SysUserVo;
import com.bid.sys.service.SysUserService;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUser findUserByLoginName(String username) {
        return sysUserMapper.findUserByLoginName(username);
    }

    @Override
    public List<SysUserVo> findDataGrid(PageInfo pageInfo, Map<String, Object> condition) {
        PageHelperUtils.startPage(pageInfo);
        return sysUserMapper.findUserPageCondition(pageInfo, condition);
    }

    @Override
    public int addUser(SysUserVo sysUserVo) {
        entryptPassword(sysUserVo);
        sysUserVo.setCreateDate(DateUtils.getSysTimestamp());
        SysUser sysUser = new SysUser();
        try {
            PropertyUtils.copyProperties(sysUser, sysUserVo);
        } catch (Exception e) {
            LOGGER.error("数据转换异常{}", e);
            throw new ServiceException("数据转换异常{}", e);
        }
        int count = sysUserMapper.insertSelective(sysUser);
        if (count == 1) {
            sysUser = sysUserMapper.findUserByLoginName(sysUser.getLoginName());
            //保存角色信息
            String roleIds = sysUserVo.getRoleIds();
            if (StringUtils.isNotBlanks(roleIds)) {
                SysUserRole sysUserRole = new SysUserRole();
                String userId = sysUser.getUserId();
                String[] roleIdArr = roleIds.split(",");
                for (String roleId : roleIdArr) {
                    if (StringUtils.isNotBlanks(roleId)) {
                        sysUserRole.setUserId(userId);
                        sysUserRole.setRoleId(roleId);
                        sysUserRoleMapper.insert(sysUserRole);
                    }
                }
            }
        }
        return count;
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(SysUserVo sysUserVo) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        sysUserVo.setSalt(Encodes.encodeHex(salt));
        byte[] hashPassword = Digests.sha1(sysUserVo.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
        sysUserVo.setPassword(Encodes.encodeHex(hashPassword));
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(SysUser sysUser, String newPassword) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        sysUser.setSalt(Encodes.encodeHex(salt));
        byte[] hashPassword = Digests.sha1(newPassword.getBytes(), salt, HASH_INTERATIONS);
        sysUser.setPassword(Encodes.encodeHex(hashPassword));
    }

    @Override
    public boolean checkPassword(SysUser user, String oldPwd) {
        byte[] salt = Encodes.decodeHex(user.getSalt());
        byte[] hashPassword = Digests.sha1(oldPwd.getBytes(), salt, HASH_INTERATIONS);
        if (user.getPassword().equals(Encodes.encodeHex(hashPassword))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateUserPassword(SysUser sysUser, String pwd) {
        entryptPassword(sysUser, pwd);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public SysUserVo findUserVoById(String userId) {
        return sysUserMapper.findUserVoById(userId);
    }

    @Override
    public int updateUserAllAndRole(SysUser sysUser, String roleIds) {
        int count = super.updateAll(sysUser);
        if (roleIds != null) {
            //删除所有角色
            sysUserRoleMapper.deleteByUserId(sysUser.getUserId());
            //保存新角色
            if (StringUtils.isNotBlanks(roleIds)) {
                SysUserRole sysUserRole = new SysUserRole();
                String userId = sysUser.getUserId();
                String[] roleIdArr = roleIds.split(",");
                for (String roleId : roleIdArr) {
                    if (StringUtils.isNotBlanks(roleId)) {
                        sysUserRole.setUserId(userId);
                        sysUserRole.setRoleId(roleId);
                        sysUserRoleMapper.insert(sysUserRole);
                    }
                }
            }
        }
        return count;
    }

}
