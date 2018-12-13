package tenttijarjestelma;

import java.io.Serializable;

import lombok.Data;

@Data
public class VastausId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3270309934754939571L;

	private Long oppilas_id;
	
	private Long vastausvaihtoehto_id;
	
	private Long tentti_id;
	
	public int hashCode() {
		return (int)(oppilas_id + vastausvaihtoehto_id + tentti_id);
	}
	
	public boolean equals(Object object) {
		if(object instanceof VastausId) {
			VastausId otherId = (VastausId) object;
			return (otherId.oppilas_id == this.oppilas_id) && 
					(otherId.vastausvaihtoehto_id == this.vastausvaihtoehto_id) &&
					(otherId.tentti_id == this.tentti_id);
		}
		return false;
	}
}
