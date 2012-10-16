package searcher;

public enum RequestMethod {
	POST("POST"), GET("GET"), PUT("PUT"), DELETE("DELETE"), HEAD("HEAD");
	
	private String stringName;
	private RequestMethod(String stringName) {
		this.stringName = stringName;
	}
	
	@Override
	public String toString() {
		return stringName;
	}
}
