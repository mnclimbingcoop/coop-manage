databaseChangeLog = {

	changeSet(author: "ajz (generated)", id: "1338333193657-1") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-2") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-3") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-4") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-5") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-6") {
		createTable(tableName: "board_member") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-7") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-8") {
		createTable(tableName: "coop_event_attendee") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-9") {
		createTable(tableName: "coop_event_coop_event_attendee") {
			column(name: "coop_event_attendees_id", type: "BIGINT")

			column(name: "coop_event_attendee_id", type: "BIGINT")
		}
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-10") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-11") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-12") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-13") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-14") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-15") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-16") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-17") {
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

			column(name: "expiration_notification_sent", type: "DATETIME")
		}
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-18") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-19") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-20") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-21") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-22") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-23") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-24") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-25") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-26") {
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

	changeSet(author: "ajz (generated)", id: "1338333193657-27") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-28") {
		addPrimaryKey(columnNames: "role_id, user_id", tableName: "user_role")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-29") {
		createIndex(indexName: "card_identifier", tableName: "access_card", unique: "true") {
			column(name: "card_identifier")
		}
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-30") {
		createIndex(indexName: "authority", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-31") {
		createIndex(indexName: "username", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-32") {
		addForeignKeyConstraint(baseColumnNames: "access_card_id", baseTableName: "access_card_assignment", baseTableSchemaName: "mncc_devel", constraintName: "FKD9449A818BC483E2", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "access_card", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-33") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "access_card_assignment", baseTableSchemaName: "mncc_devel", constraintName: "FKD9449A8195F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-34") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "automobile", baseTableSchemaName: "mncc_devel", constraintName: "FK15412F1195F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-35") {
		addForeignKeyConstraint(baseColumnNames: "coop_event_attendee_id", baseTableName: "coop_event_coop_event_attendee", baseTableSchemaName: "mncc_devel", constraintName: "FK14A5F45AB1E541F1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "coop_event_attendee", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-36") {
		addForeignKeyConstraint(baseColumnNames: "coop_event_attendees_id", baseTableName: "coop_event_coop_event_attendee", baseTableSchemaName: "mncc_devel", constraintName: "FK14A5F45A5856FEC6", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "coop_event", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-37") {
		addForeignKeyConstraint(baseColumnNames: "payment_type_id", baseTableName: "day_pass", baseTableSchemaName: "mncc_devel", constraintName: "FK731B7AD4A6E288C8", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "payment_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-38") {
		addForeignKeyConstraint(baseColumnNames: "sponsor_id", baseTableName: "day_pass", baseTableSchemaName: "mncc_devel", constraintName: "FK731B7AD48E24DFB4", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-39") {
		addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "emergency_contact", baseTableSchemaName: "mncc_devel", constraintName: "FK328D6F7233D8C91B", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "address", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-40") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "emergency_contact", baseTableSchemaName: "mncc_devel", constraintName: "FK328D6F7295F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-41") {
		addForeignKeyConstraint(baseColumnNames: "instrument_id", baseTableName: "instrument_receipt", baseTableSchemaName: "mncc_devel", constraintName: "FKE3ECA5E0337B0D9", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "instrument", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-42") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "instrument_receipt", baseTableSchemaName: "mncc_devel", constraintName: "FKE3ECA5E095F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-43") {
		addForeignKeyConstraint(baseColumnNames: "access_type_id", baseTableName: "payment", baseTableSchemaName: "mncc_devel", constraintName: "FKD11C320637EF1BA2", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "access_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-44") {
		addForeignKeyConstraint(baseColumnNames: "class_of_stock_id", baseTableName: "payment", baseTableSchemaName: "mncc_devel", constraintName: "FKD11C320694335086", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "stock_class", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-45") {
		addForeignKeyConstraint(baseColumnNames: "payment_type_id", baseTableName: "payment", baseTableSchemaName: "mncc_devel", constraintName: "FKD11C3206A6E288C8", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "payment_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-46") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "payment", baseTableSchemaName: "mncc_devel", constraintName: "FKD11C320695F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-47") {
		addForeignKeyConstraint(baseColumnNames: "termination_reason_id", baseTableName: "payment", baseTableSchemaName: "mncc_devel", constraintName: "FKD11C320650A3780E", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "membership_termination_reason", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-48") {
		addForeignKeyConstraint(baseColumnNames: "type_id", baseTableName: "payment", baseTableSchemaName: "mncc_devel", constraintName: "FKD11C32061D94556F", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "membership_type", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-49") {
		addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "person", baseTableSchemaName: "mncc_devel", constraintName: "FKC4E39B5533D8C91B", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "address", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-50") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "person", baseTableSchemaName: "mncc_devel", constraintName: "FKC4E39B5570D10199", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-51") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "purchase", baseTableSchemaName: "mncc_devel", constraintName: "FK67E9050195F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-52") {
		addForeignKeyConstraint(baseColumnNames: "purchase_id", baseTableName: "purchase_item", baseTableSchemaName: "mncc_devel", constraintName: "FKB11327916F9EF659", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "purchase", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-53") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "sign_in", baseTableSchemaName: "mncc_devel", constraintName: "FK7C785EE795F26999", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "person", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-54") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", baseTableSchemaName: "mncc_devel", constraintName: "FK143BF46ACBA63DB9", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}

	changeSet(author: "ajz (generated)", id: "1338333193657-55") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", baseTableSchemaName: "mncc_devel", constraintName: "FK143BF46A70D10199", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "mncc_devel", referencesUniqueColumn: "false")
	}
}
