package com.kaptsiug.blog.repository.redis;

import com.kaptsiug.blog.entity.redis.ActivationCodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationCodeRepository extends CrudRepository<ActivationCodeEntity, String> {

}
