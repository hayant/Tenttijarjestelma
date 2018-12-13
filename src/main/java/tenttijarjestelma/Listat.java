package tenttijarjestelma;

import java.util.ArrayList;
import java.util.List;

public class Listat {

	public static List<Object> iterableToArrayList(Iterable<Object> iterable) {
		
		List<Object> list = new ArrayList<>();
		
		for(Object object : iterable) {
			list.add(object);
		}
		
		return list;
	}
}
