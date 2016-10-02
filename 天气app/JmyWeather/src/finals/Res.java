package finals;

public class Res {
private String reson;
private Result result;
private int  error_code;
public String getReson() {
	return reson;
}
public void setReson(String reson) {
	this.reson = reson;
}
public Result getResult() {
	return result;
}
public void setResult(Result result) {
	this.result = result;
}
public int getError_code() {
	return error_code;
}
public void setError_code(int error_code) {
	this.error_code = error_code;
}
@Override
public String toString() {
	return "Res [reson=" + reson + ", result=" + result + ", error_code="
			+ error_code + "]";
}

}
