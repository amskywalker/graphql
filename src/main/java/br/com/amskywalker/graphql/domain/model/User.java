package br.com.amskywalker.graphql.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record User(UUID uuid, String name, LocalDateTime birthDate, String motherName) {
}
