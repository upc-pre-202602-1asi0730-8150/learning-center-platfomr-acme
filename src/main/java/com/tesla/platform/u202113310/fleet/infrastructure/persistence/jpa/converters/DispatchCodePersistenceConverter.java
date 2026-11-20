package com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.converters;

import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.DispatchCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts {@link DispatchCode} to and from its column representation.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Converter(autoApply = false)
public class DispatchCodePersistenceConverter implements AttributeConverter<DispatchCode, String> {

    @Override
    public String convertToDatabaseColumn(DispatchCode attribute) {
        return attribute == null ? null : attribute.code();
    }

    @Override
    public DispatchCode convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new DispatchCode(dbData);
    }
}
