package grsu.epam.utilitycompany.app.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grsu.epam.utilitycompany.app.domain.Dispatcher;
import grsu.epam.utilitycompany.app.repository.DispatcherDao;
import grsu.epam.utilitycompany.app.service.DispatcherService;

@Service
@Transactional

public class DispatcherServiceImpl implements DispatcherService {

	@Autowired
	private DispatcherDao dispatcherRepository;

	@Override
	public void saveDispatcher(Dispatcher dispatcher) {
		dispatcherRepository.save(dispatcher);
		
	}

	@Override
	public void deleteDispatcher(Dispatcher dispatcher) {
		dispatcherRepository.delete(dispatcher);
		
	}

	@Override
	public Dispatcher getDispatcherByID(Long dispatcherId) {
		return dispatcherRepository.findById(dispatcherId);
	}

	@Override
	public List<Dispatcher> getDispatchers() {
		List<Dispatcher> dispatchers = dispatcherRepository.findAll();		
		return dispatchers;
	}

	@Override
	public void updateDispatcher(Dispatcher dispatcher) {
		dispatcherRepository.update(dispatcher);
		
	}
}
