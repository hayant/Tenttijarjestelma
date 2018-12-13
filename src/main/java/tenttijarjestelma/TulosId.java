package tenttijarjestelma;

import java.io.Serializable;

public class TulosId implements Serializable {

	private Long oppilas_id;
	private Long tenttikysymys_id;
	private Long tentti_id;
	
	public TulosId() {
	}

	public int hashCode() {
		return (int) (oppilas_id + tenttikysymys_id + tentti_id);
	}

	public boolean equals(Object object) {
		if (object instanceof TulosId) {
			TulosId otherId = (TulosId) object;
			return (otherId.oppilas_id == this.oppilas_id) && (otherId.tenttikysymys_id == this.tenttikysymys_id) && (otherId.tentti_id == this.tentti_id);
		}
		return false;

	}

}