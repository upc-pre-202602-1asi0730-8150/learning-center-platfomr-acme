package com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.converters;

import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.VehicleId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

/**
 * Converts {@link VehicleId} to and from its column representation (UUID text).
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Converter(autoApply = false)
public class VehicleIdPersistenceConverter implements AttributeConverter<VehicleId, String> {

    @Override
    public String convertToDatabaseColumn(VehicleId attribute) {
        return attribute == null ? null : attribute.value().toString();
    }

    @Override
    public VehicleId convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new VehicleId(UUID.fromString(dbData));
    }
}
