package br.com.amskywalker.graphql.domain.model;

import java.util.List;
import java.util.UUID;

public record Account(UUID uuid, Double balance, AccountType type, List<User> users) {
}
