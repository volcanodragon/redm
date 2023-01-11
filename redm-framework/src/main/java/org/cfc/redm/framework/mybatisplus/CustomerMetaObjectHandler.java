package org.cfc.redm.framework.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.cfc.redm.framework.security.userdetails.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomerMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser != null) {
            this.strictInsertFill(metaObject, "creatorId", Integer.class, currentUser.getCreatorId());
            this.strictInsertFill(metaObject, "creator", String.class, currentUser.getCreator());
        } else {
            this.strictInsertFill(metaObject, "creatorId", Integer.class, 0);
            this.strictInsertFill(metaObject, "creator", String.class, "系统操作");
        }
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
