package grsu.epam.utilitycompany.app.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import grsu.epam.utilitycompany.app.domain.UserPrincipal;
import grsu.epam.utilitycompany.app.repository.UserPrincipalDao;
import grsu.epam.utilitycompany.app.repository.base.AbstractHibernateDao;

@Repository
@Transactional
public class UserPrincipalRepository extends
		AbstractHibernateDao<UserPrincipal, Long> implements UserPrincipalDao {
	@Override
	public UserPrincipal findById(Long userId) {
		Criteria cr = getSession().createCriteria(UserPrincipal.class, "user")
				.add(Restrictions.eq("userId", userId));
		return (UserPrincipal) cr.uniqueResult();
	}

	public UserPrincipal findByCredentials(String login, String password) {
		Criteria cr = getSession().createCriteria(UserPrincipal.class, "user")
				.add(Restrictions.eq("login", login))
				.add(Restrictions.eq("password", password));
		return (UserPrincipal) cr.uniqueResult();
	}

	@Override
	public UserPrincipal findByLogin(String login) {
		Criteria cr = getSession().createCriteria(UserPrincipal.class, "user")
				.add(Restrictions.eq("login", login));
		return (UserPrincipal) cr.uniqueResult();
	}
}
