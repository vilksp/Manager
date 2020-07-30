package lt.management.oms.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image extends BaseEntity {

	@JsonView(View.FileInfo.class)
	private String imageName;
	@JsonView(View.FileInfo.class)
	private String imageType;

	@Lob
	private byte[] data;

}
