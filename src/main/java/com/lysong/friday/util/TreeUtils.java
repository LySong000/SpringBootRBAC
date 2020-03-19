package com.lysong.friday.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lysong.friday.model.SysPermission;
import java.util.List;

public class TreeUtils {

    /**
     * 权限菜单树
     *
     * @param parentId
     * @param permissionsAll
     * @param array
     */
    public static void setPermissionsTree(Integer parentId, List<SysPermission> permissionsAll, JSONArray array) {
        //先取出permissionid
        for (SysPermission per : permissionsAll) {
            if (per.getParentId().equals(parentId)) {
                //转成json
                String string = JSONObject.toJSONString(per);
                //设成父节点
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                //加入到Array
                array.add(parent);
                for (SysPermission p:
                     permissionsAll) {
                    if(p.getParentId() == per.getId()){
                        JSONArray child = new JSONArray();
                        //添加键值对
                        parent.put("child", child);
                        //递归查找
                        setPermissionsTree(per.getId(), permissionsAll, child);
                    }
                }

            }
        }
    }
}
