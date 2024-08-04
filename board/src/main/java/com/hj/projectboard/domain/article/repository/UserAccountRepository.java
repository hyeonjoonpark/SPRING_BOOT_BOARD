package com.hj.projectboard.domain.article.repository;

import com.hj.projectboard.domain.user.UserAccount;
import com.hj.projectboard.domain.user.UserAccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = UserAccountProjection.class)
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

}
