package mng.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@Column(columnDefinition = "boolean default false")
	private Boolean status;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="renter_id",nullable = false)
	private Renter renter;
	
//	@OneToMany(mappedBy = "property_id",fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
//	private List<Document> docs = new ArrayList<>();
//	
	
}
