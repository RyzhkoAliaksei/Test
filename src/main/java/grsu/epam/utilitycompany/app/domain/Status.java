package grsu.epam.utilitycompany.app.domain;

public enum Status {
	accept, 
	in_processing,
	executed,
	cancelled;
	  public String getString() {
	        return this.name();
	    }
}
