package com.ioproject.carent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	@Id
	private ObjectId id;
	private int roleId;
	private String name;

	public Role(String name) {
		super();
		id = new ObjectId();
		if(name=="ROLE_USER"){
			setRoleId(1);
		}
		if(name=="ROLE_ADMIN"){
			setRoleId(2);
		}
		this.name = name;
	}
}
