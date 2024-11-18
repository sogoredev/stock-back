package com.gds.Gestion.de.stock.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gds.Gestion.de.stock.entites.UserRole;

import java.io.IOException;

public class UserRoleDeserializer extends JsonDeserializer<TypeRole> {

    @Override
    public TypeRole deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        String value = p.getText();
        return TypeRole.valueOf(value.toUpperCase());
    }
}

