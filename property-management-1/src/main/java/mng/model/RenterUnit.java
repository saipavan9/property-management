package mng.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Entity
@RequiredArgsConstructor
@ToString
public class RenterUnit {

	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique = true)
	private String propertyName;
	
	private String address;
	
	private int noOfbeds;
	
	private String sizeOfProperty;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="renter_id",nullable = false)
	private Renter renter;
	
}
