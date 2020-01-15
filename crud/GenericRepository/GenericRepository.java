package com.generic.crud.GenericRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface GenericRepository<S> extends JpaRepository<S,Long> ,JpaSpecificationExecutor<S> {

}
