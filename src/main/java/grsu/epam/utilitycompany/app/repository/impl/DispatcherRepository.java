package grsu.epam.utilitycompany.app.repository.impl;

import org.springframework.stereotype.Repository;

import grsu.epam.utilitycompany.app.domain.Dispatcher;
import grsu.epam.utilitycompany.app.repository.DispatcherDao;
import grsu.epam.utilitycompany.app.repository.base.AbstractHibernateDao;

@Repository
public class DispatcherRepository extends AbstractHibernateDao<Dispatcher, Long> implements DispatcherDao {

}
