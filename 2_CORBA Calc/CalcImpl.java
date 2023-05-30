import CalcModule.CalcPOA;
import java.util.*;

public class CalcImpl extends CalcPOA {
	public CalcImpl() {
		super();
		System.out.println("Calculator object created");
	}

	public float add(float num1, float num2) {
		return num1 + num2;
	}
}