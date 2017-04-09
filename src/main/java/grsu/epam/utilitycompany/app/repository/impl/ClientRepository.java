package grsu.epam.utilitycompany.app.repository.impl;

import grsu.epam.utilitycompany.app.domain.Client;
import grsu.epam.utilitycompany.app.repository.ClientDao;
import grsu.epam.utilitycompany.app.repository.base.AbstractHibernateDao;

import org.springframework.stereotype.Repository;


@Repository
public class ClientRepository extends AbstractHibernateDao<Client, Long> implements ClientDao {
	
	
}

