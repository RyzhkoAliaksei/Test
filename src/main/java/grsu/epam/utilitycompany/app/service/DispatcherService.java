package grsu.epam.utilitycompany.app.service;

import grsu.epam.utilitycompany.app.domain.Dispatcher;

import java.util.List;

public interface DispatcherService {

	void saveDispatcher(Dispatcher dispatcher);

	void deleteDispatcher(Dispatcher dispatcher);

	Dispatcher getDispatcherByID(Long dispatcherId);

	List<Dispatcher> getDispatchers();

	void updateDispatcher(Dispatcher dispatcher);

}
