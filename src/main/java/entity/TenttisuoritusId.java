package entity;

import java.io.Serializable;

public class TenttisuoritusId implements Serializable {

	private Long oppilas_id;

	private Long tentti_id;

	public int hashCode() {
		return (int) (oppilas_id + tentti_id);
	}

	public boolean equals(Object object) {
		if (object instanceof TenttisuoritusId) {
			TenttisuoritusId otherId = (TenttisuoritusId) object;
			return (otherId.oppilas_id == this.oppilas_id) && (otherId.tentti_id == this.tentti_id);
		}
		return false;
	}
}
