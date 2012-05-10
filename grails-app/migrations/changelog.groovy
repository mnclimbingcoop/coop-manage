databaseChangeLog = {

	changeSet(author: "ajz (generated)", id: "1336610735587-1") {
		createTable(tableName: "access_card") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "card_identifier", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "label", type: "VARCHAR(255)")

			column(name: "facility_assigned", type: "BIT")

			column(name: "facility_code", type: "VARCHAR(255)")

			column(name: "lost", type: "BIT")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-2") {
		createTable(tableName: "access_card_assignment") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "access_card_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "issue_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "lost", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "person_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "return_date", type: "DATETIME")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-3") {
		createTable(tableName: "access_pass_form") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "accepted_code_entered", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "accepted_code_generated", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "accepted_terms", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "access_purchased_access_duration", type: "INT")

			column(name: "access_purchased_access_type_id", type: "BIGINT")

			column(name: "access_purchased_amount", type: "DECIMAL(19,2)")

			column(name: "access_purchased_end_date", type: "DATETIME")

			column(name: "access_purchased_payment_date", type: "DATETIME")

			column(name: "access_purchased_payment_type_id", type: "BIGINT")

			column(name: "access_purchased_access_person_id", type: "BIGINT")

			column(name: "access_purchased_start_date", type: "DATETIME")

			column(name: "access_purchased_tax_rate", type: "DECIMAL(5,2)")

			column(name: "access_purchased_taxable", type: "BIT")

			column(name: "access_purchased_transaction_id", type: "VARCHAR(255)")

			column(name: "address_address1", type: "VARCHAR(40)") {
				constraints(nullable: "false")
			}

			column(name: "address_address2", type: "VARCHAR(40)")

			column(name: "address_city", type: "VARCHAR(30)") {
				constraints(nullable: "false")
			}

			column(name: "address_get_city_state_zip", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "address_get_full_zipcode", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "address_state", type: "VARCHAR(2)") {
				constraints(nullable: "false")
			}

			column(name: "address_zip4", type: "VARCHAR(4)")

			column(name: "address_zipcode", type: "VARCHAR(5)")

			column(name: "agreed_to_waiver", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "automobile_color", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "automobile_licence_plate_number", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "automobile_licence_plate_state", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "automobile_make", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "automobile_model", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "automobile_person_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "automobile_year", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "completed_by", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "completed_from_address", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_completed", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "emergency_contact_address_id", type: "BIGINT")

			column(name: "emergency_contact_first_name", type: "VARCHAR(30)") {
				constraints(nullable: "false")
			}

			column(name: "emergency_contact_last_name", type: "VARCHAR(30)")

			column(name: "emergency_contact_middle_name", type: "VARCHAR(20)")

			column(name: "emergency_contact_person_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "emergency_contact_phone_number", type: "VARCHAR(255)")

			column(name: "emergency_contact_relation", type: "VARCHAR(255)")

			column(name: "emergency_contact_suffix", type: "VARCHAR(10)")

			column(name: "emergency_contact_title", type: "VARCHAR(10)")

			column(name: "emergency_contact_address_address1", type: "VARCHAR(40)") {
				constraints(nullable: "false")
			}

			column(name: "emergency_contact_address_address2", type: "VARCHAR(40)")

			column(name: "emergency_contact_address_city", type: "VARCHAR(30)") {
				constraints(nullable: "false")
			}

			column(name: "emergency_contact_address_get_city_state_zip", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "emergency_contact_address_get_full_zipcode", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "emergency_contact_address_state", type: "VARCHAR(2)") {
				constraints(nullable: "false")
			}

			column(name: "emergency_contact_address_zip4", type: "VARCHAR(4)")

			column(name: "emergency_contact_address_zipcode", type: "VARCHAR(5)")

			column(name: "guardian_accepted", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "guardian_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "key_fob_number", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "membership_amount", type: "DECIMAL(19,2)")

			column(name: "membership_member_id", type: "VARCHAR(255)")

			column(name: "membership_membership_from", type: "DATETIME")

			column(name: "membership_membership_to", type: "DATETIME")

			column(name: "membership_payment_date", type: "DATETIME")

			column(name: "membership_payment_type_id", type: "BIGINT")

			column(name: "membership_membership_person_id", type: "BIGINT")

			column(name: "membership_tax_rate", type: "DECIMAL(5,2)")

			column(name: "membership_taxable", type: "BIT")

			column(name: "membership_termination_reason_id", type: "BIGINT")

			column(name: "membership_transaction_id", type: "VARCHAR(255)")

			column(name: "membership_type_id", type: "BIGINT")

			column(name: "membership_form_abbreviation", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "membership_form_current_version", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "membership_form_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "membership_form_obsoletion_date", type: "DATETIME")

			column(name: "membership_form_required", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "membership_form_version_date", type: "DATETIME")

			column(name: "new_member", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "orientation_abbreviation", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "orientation_current_version", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "orientation_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "orientation_obsoletion_date", type: "DATETIME")

			column(name: "orientation_required", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "orientation_version_date", type: "DATETIME")

			column(name: "payment_amount", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "payment_payment_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "payment_payment_type_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "payment_person_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "payment_tax_rate", type: "DECIMAL(5,2)") {
				constraints(nullable: "false")
			}

			column(name: "payment_taxable", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "payment_transaction_id", type: "VARCHAR(255)")

			column(name: "person_address_id", type: "BIGINT")

			column(name: "person_birth_date", type: "DATETIME")

			column(name: "person_email_address", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "person_first_name", type: "VARCHAR(30)") {
				constraints(nullable: "false")
			}

			column(name: "person_last_name", type: "VARCHAR(30)")

			column(name: "person_middle_name", type: "VARCHAR(20)")

			column(name: "person_nick_name", type: "VARCHAR(255)")

			column(name: "person_phone_number", type: "VARCHAR(255)")

			column(name: "person_suffix", type: "VARCHAR(10)")

			column(name: "person_title", type: "VARCHAR(10)")

			column(name: "person_user_id", type: "BIGINT")

			column(name: "reviewed_orientation", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "waiver_abbreviation", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "waiver_current_version", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "waiver_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "waiver_obsoletion_date", type: "DATETIME")

			column(name: "waiver_required", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "waiver_version_date", type: "DATETIME")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-4") {
		createTable(tableName: "access_type") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "duration", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-5") {
		createTable(tableName: "address") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "address1", type: "VARCHAR(40)") {
				constraints(nullable: "false")
			}

			column(name: "address2", type: "VARCHAR(40)")

			column(name: "city", type: "VARCHAR(30)") {
				constraints(nullable: "false")
			}

			column(name: "get_city_state_zip", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "get_full_zipcode", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "VARCHAR(2)") {
				constraints(nullable: "false")
			}

			column(name: "zip4", type: "VARCHAR(4)")

			column(name: "zipcode", type: "VARCHAR(5)")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-6") {
		createTable(tableName: "automobile") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "color", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "licence_plate_number", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "licence_plate_state", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "make", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "model", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "person_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "year", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-7") {
		createTable(tableName: "board_member") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-8") {
		createTable(tableName: "coop_event") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "early_registration_deadline", type: "DATETIME")

			column(name: "event_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "max_registrants", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-9") {
		createTable(tableName: "coop_event_attendee") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-10") {
		createTable(tableName: "coop_event_coop_event_attendee") {
			column(name: "coop_event_attendees_id", type: "BIGINT")

			column(name: "coop_event_attendee_id", type: "BIGINT")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-11") {
		createTable(tableName: "day_pass") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "comments", type: "VARCHAR(255)")

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "pass_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "payment_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "payment_type_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "sponsor_id", type: "BIGINT")

			column(name: "tax_rate", type: "DECIMAL(5,2)") {
				constraints(nullable: "false")
			}

			column(name: "taxable", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "transaction_id", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-12") {
		createTable(tableName: "emergency_contact") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "address_id", type: "BIGINT")

			column(name: "first_name", type: "VARCHAR(30)") {
				constraints(nullable: "false")
			}

			column(name: "last_name", type: "VARCHAR(30)")

			column(name: "middle_name", type: "VARCHAR(20)")

			column(name: "person_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "phone_number", type: "VARCHAR(255)")

			column(name: "relation", type: "VARCHAR(255)")

			column(name: "suffix", type: "VARCHAR(10)")

			column(name: "title", type: "VARCHAR(10)")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-13") {
		createTable(tableName: "hid_door_event") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "door_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "event_code", type: "INT")

			column(name: "event_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "event_subject", type: "VARCHAR(255)")

			column(name: "ip_address", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "upload_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(defaultValueBoolean: "false", name: "imported", type: "BIT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-14") {
		createTable(tableName: "instrument") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "abbreviation", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "current_version", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "version_date", type: "DATETIME")

			column(name: "required", type: "BIT")

			column(name: "obsoletion_date", type: "DATETIME")

			column(name: "days_valid_for", type: "INT")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-15") {
		createTable(tableName: "instrument_receipt") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "instrument_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "outgoing", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "person_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "transaction_date", type: "DATETIME") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-16") {
		createTable(tableName: "membership_termination_reason") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "allow_reactivation", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-17") {
		createTable(tableName: "membership_type") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "expires", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-18") {
		createTable(tableName: "payment") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "payment_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "payment_type_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "person_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "transaction_id", type: "VARCHAR(255)")

			column(name: "class", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "access_duration", type: "INT")

			column(name: "access_type_id", type: "BIGINT")

			column(name: "end_date", type: "DATETIME")

			column(name: "start_date", type: "DATETIME")

			column(name: "comments", type: "VARCHAR(255)")

			column(name: "class_of_stock_id", type: "BIGINT")

			column(name: "member_id", type: "VARCHAR(255)")

			column(name: "membership_from", type: "DATETIME")

			column(name: "membership_to", type: "DATETIME")

			column(name: "termination_reason_id", type: "BIGINT")

			column(name: "type_id", type: "BIGINT")

			column(name: "tax_rate", type: "DECIMAL(5,2)")

			column(name: "taxable", type: "BIT")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-19") {
		createTable(tableName: "payment_type") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "process_fee_charge", type: "BIT")

			column(name: "processing_fee", type: "DECIMAL(19,2)")

			column(name: "processing_rate", type: "DECIMAL(5,2)")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-20") {
		createTable(tableName: "person") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "address_id", type: "BIGINT")

			column(name: "birth_date", type: "DATETIME")

			column(name: "email_address", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "first_name", type: "VARCHAR(30)") {
				constraints(nullable: "false")
			}

			column(name: "last_name", type: "VARCHAR(30)")

			column(name: "middle_name", type: "VARCHAR(20)")

			column(name: "phone_number", type: "VARCHAR(255)")

			column(name: "suffix", type: "VARCHAR(10)")

			column(name: "title", type: "VARCHAR(10)")

			column(name: "user_id", type: "BIGINT")

			column(name: "nick_name", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-21") {
		createTable(tableName: "purchase") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "comments", type: "VARCHAR(255)")

			column(name: "merchant", type: "VARCHAR(255)")

			column(name: "paid", type: "DATETIME")

			column(name: "person_id", type: "BIGINT")

			column(name: "purchase_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "purchaser", type: "VARCHAR(255)")

			column(name: "shipping", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "tax", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-22") {
		createTable(tableName: "purchase_item") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "count", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "purchase_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-23") {
		createTable(tableName: "purchase_purchase_item") {
			column(name: "purchase_items_id", type: "BIGINT")

			column(name: "purchase_item_id", type: "BIGINT")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-24") {
		createTable(tableName: "robot_password") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ip_address_starts_with", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "secret", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-25") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-26") {
		createTable(tableName: "sign_in") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "full_name", type: "VARCHAR(255)")

			column(name: "guest", type: "BIT")

			column(name: "member", type: "BIT")

			column(name: "person_id", type: "BIGINT")

			column(name: "sign_in_date", type: "DATETIME")

			column(name: "visitor", type: "BIT")

			column(name: "source", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-27") {
		createTable(tableName: "stock_class") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "abbreviation", type: "VARCHAR(1)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-28") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-29") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-30") {
		addPrimaryKey(columnNames: "role_id, user_id", tableName: "user_role")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-31") {
		createIndex(indexName: "card_identifier", tableName: "access_card", unique: "true") {
			column(name: "card_identifier")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-32") {
		createIndex(indexName: "authority", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-33") {
		createIndex(indexName: "username", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-34") {
		addForeignKeyConstraint(baseColumnNames: "access_card_id", baseTableName: "access_card_assignment", baseTableSchemaName: "mncc_devel", constraintName: "FKD9449A818BC483E2", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "access_card", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-35") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "access_card_assignment", baseTableSchemaName: "mncc_devel", constraintName: "FKD9449A8195F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-36") {
		addForeignKeyConstraint(baseColumnNames: "access_purchased_access_person_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B787176DFFCD75", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-37") {
		addForeignKeyConstraint(baseColumnNames: "access_purchased_access_type_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B78717928E0E59", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "access_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-38") {
		addForeignKeyConstraint(baseColumnNames: "access_purchased_payment_type_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B78717A021ECF1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "payment_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-39") {
		addForeignKeyConstraint(baseColumnNames: "automobile_person_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B787175535B1EB", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-40") {
		addForeignKeyConstraint(baseColumnNames: "emergency_contact_address_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B787178BD9F408", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "address", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-41") {
		addForeignKeyConstraint(baseColumnNames: "emergency_contact_person_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B78717DAD9AD0C", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-42") {
		addForeignKeyConstraint(baseColumnNames: "membership_membership_person_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B78717587B8D39", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-43") {
		addForeignKeyConstraint(baseColumnNames: "membership_payment_type_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B787173432FA5F", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "payment_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-44") {
		addForeignKeyConstraint(baseColumnNames: "membership_termination_reason_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B7871775E81C65", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "membership_termination_reason", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-45") {
		addForeignKeyConstraint(baseColumnNames: "membership_type_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B78717F4FF4E06", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "membership_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-46") {
		addForeignKeyConstraint(baseColumnNames: "payment_payment_type_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B787178677448F", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "payment_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-47") {
		addForeignKeyConstraint(baseColumnNames: "payment_person_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B78717839546A0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-48") {
		addForeignKeyConstraint(baseColumnNames: "person_address_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B78717F1108C45", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "address", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-49") {
		addForeignKeyConstraint(baseColumnNames: "person_user_id", baseTableName: "access_pass_form", baseTableSchemaName: "mncc_devel", constraintName: "FK70B78717D9321EAF", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-50") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "automobile", baseTableSchemaName: "mncc_devel", constraintName: "FK15412F1195F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-51") {
		addForeignKeyConstraint(baseColumnNames: "coop_event_attendee_id", baseTableName: "coop_event_coop_event_attendee", baseTableSchemaName: "mncc_devel", constraintName: "FK14A5F45AB1E541F1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "coop_event_attendee", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-52") {
		addForeignKeyConstraint(baseColumnNames: "coop_event_attendees_id", baseTableName: "coop_event_coop_event_attendee", baseTableSchemaName: "mncc_devel", constraintName: "FK14A5F45A5856FEC6", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "coop_event", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-53") {
		addForeignKeyConstraint(baseColumnNames: "payment_type_id", baseTableName: "day_pass", baseTableSchemaName: "mncc_devel", constraintName: "FK731B7AD4A6E288C8", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "payment_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-54") {
		addForeignKeyConstraint(baseColumnNames: "sponsor_id", baseTableName: "day_pass", baseTableSchemaName: "mncc_devel", constraintName: "FK731B7AD48E24DFB4", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-55") {
		addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "emergency_contact", baseTableSchemaName: "mncc_devel", constraintName: "FK328D6F7233D8C91B", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "address", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-56") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "emergency_contact", baseTableSchemaName: "mncc_devel", constraintName: "FK328D6F7295F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-57") {
		addForeignKeyConstraint(baseColumnNames: "instrument_id", baseTableName: "instrument_receipt", baseTableSchemaName: "mncc_devel", constraintName: "FKE3ECA5E0337B0D9", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "instrument", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-58") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "instrument_receipt", baseTableSchemaName: "mncc_devel", constraintName: "FKE3ECA5E095F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-59") {
		addForeignKeyConstraint(baseColumnNames: "access_type_id", baseTableName: "payment", baseTableSchemaName: "mncc_devel", constraintName: "FKD11C320637EF1BA2", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "access_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-60") {
		addForeignKeyConstraint(baseColumnNames: "class_of_stock_id", baseTableName: "payment", baseTableSchemaName: "mncc_devel", constraintName: "FKD11C320694335086", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "stock_class", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-61") {
		addForeignKeyConstraint(baseColumnNames: "payment_type_id", baseTableName: "payment", baseTableSchemaName: "mncc_devel", constraintName: "FKD11C3206A6E288C8", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "payment_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-62") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "payment", baseTableSchemaName: "mncc_devel", constraintName: "FKD11C320695F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-63") {
		addForeignKeyConstraint(baseColumnNames: "termination_reason_id", baseTableName: "payment", baseTableSchemaName: "mncc_devel", constraintName: "FKD11C320650A3780E", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "membership_termination_reason", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-64") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "payment", baseTableSchemaName: "mncc_devel", constraintName: "FKD11C32061D94556F", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "membership_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-65") {
		addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "person", baseTableSchemaName: "mncc_devel", constraintName: "FKC4E39B5533D8C91B", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "address", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-66") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "person", baseTableSchemaName: "mncc_devel", constraintName: "FKC4E39B5570D10199", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-67") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "purchase", baseTableSchemaName: "mncc_devel", constraintName: "FK67E9050195F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-68") {
		addForeignKeyConstraint(baseColumnNames: "purchase_id", baseTableName: "purchase_item", baseTableSchemaName: "mncc_devel", constraintName: "FKB11327916F9EF659", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "purchase", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-69") {
		addForeignKeyConstraint(baseColumnNames: "purchase_item_id", baseTableName: "purchase_purchase_item", baseTableSchemaName: "mncc_devel", constraintName: "FK178A8D3AC7158FC", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "purchase_item", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-70") {
		addForeignKeyConstraint(baseColumnNames: "purchase_items_id", baseTableName: "purchase_purchase_item", baseTableSchemaName: "mncc_devel", constraintName: "FK178A8D36738E4B8", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "purchase", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-71") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "sign_in", baseTableSchemaName: "mncc_devel", constraintName: "FK7C785EE795F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-72") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", baseTableSchemaName: "mncc_devel", constraintName: "FK143BF46ACBA63DB9", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1336610735587-73") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", baseTableSchemaName: "mncc_devel", constraintName: "FK143BF46A70D10199", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}
}
