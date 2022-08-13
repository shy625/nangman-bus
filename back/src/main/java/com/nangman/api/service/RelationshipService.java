package com.nangman.api.service;

import com.nangman.api.dto.RelationshipDto;
import org.springframework.stereotype.Service;

@Service
public interface RelationshipService {
    RelationshipDto.Info createRelationShip(long srcUserId, long targetUserId, String sessionId);
}
