/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.compare.pg.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A customer.
 * 
 * @author Oliver Gierke
 */

@Entity
public class Customer extends AbstractEntity {

	@Column(name="first_name")
	private String firstname;
	@Column(name="last_name")
	String lastname;

	@Column(name = "email_address", unique = true)
	private String emailAddress;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "customer_id")
	private Set<Address> addresses = new HashSet<Address>();

	/**
	 * Creates a new {@link Customer} from the given firstname and lastname.
	 * 
	 * @param firstname must not be {@literal null} or empty.
	 * @param lastname must not be {@literal null} or empty.
	 */
	public Customer(String firstname, String lastname) {

		Assert.hasText(firstname);
		Assert.hasText(lastname);

		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Customer(Long id, String firstname, String lastname) {

		Assert.hasText(firstname);
		Assert.hasText(lastname);

		this.firstname = firstname;
		this.lastname = lastname;
		setId(id);
	}

	protected Customer() {

	}


	public void add(Address address) {

		Assert.notNull(address);
		this.addresses.add(address);
	}

	/**
	 * Returns the firstname of the {@link Customer}.
	 * 
	 * @return
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Returns the lastname of the {@link Customer}.
	 * 
	 * @return
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Sets the lastname of the {@link Customer}.
	 * 
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Return the {@link Customer}'s addresses.
	 * 
	 * @return
	 */
	public Set<Address> getAddresses() {
		return Collections.unmodifiableSet(addresses);
	}
}
