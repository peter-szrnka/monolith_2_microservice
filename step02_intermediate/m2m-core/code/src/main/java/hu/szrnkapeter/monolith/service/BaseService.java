package hu.szrnkapeter.monolith.service;

import org.springframework.beans.factory.annotation.Autowired;

import hu.szrnkapeter.monolith.dao.AbstractDao;

/**
 * Abstract service class.
 * 
 * @author Peter Szrnka
 *
 * @param <T> The type of the result Dto.
 */
public abstract class BaseService<T,D extends AbstractDao<T>> {

	@Autowired
	protected D dao;
}