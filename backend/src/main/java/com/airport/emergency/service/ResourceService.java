package com.airport.emergency.service;

import com.airport.emergency.dto.ResourceDTO;
import com.airport.emergency.entity.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 资源服务接口
 */
public interface ResourceService {
    /**
     * 创建资源
     */
    ResourceDTO createResource(ResourceDTO resourceDTO);

    /**
     * 获取资源详情
     */
    Optional<ResourceDTO> getResourceById(Long id);

    /**
     * 根据资源代码获取资源
     */
    Optional<ResourceDTO> getResourceByCode(String code);

    /**
     * 获取所有资源
     */
    List<ResourceDTO> getAllResources();

    /**
     * 根据类型获取资源
     */
    List<ResourceDTO> getResourcesByType(Resource.ResourceType type);

    /**
     * 根据分类获取资源
     */
    List<ResourceDTO> getResourcesByCategory(Resource.ResourceCategory category);

    /**
     * 根据所有者获取资源
     */
    List<ResourceDTO> getResourcesByOwner(Long ownerId);

    /**
     * 获取可用资源
     */
    List<ResourceDTO> getAvailableResources();

    /**
     * 获取附近的可用资源
     */
    List<ResourceDTO> getNearbyResources(Double latitude, Double longitude, Double radius);

    /**
     * 分配资源到事件
     */
    ResourceDTO assignResourceToEvent(Long resourceId, Long eventId);

    /**
     * 释放资源
     */
    ResourceDTO releaseResource(Long resourceId);

    /**
     * 更新资源状态
     */
    ResourceDTO updateResourceStatus(Long resourceId, Resource.ResourceStatus status);

    /**
     * 更新资源信息
     */
    ResourceDTO updateResource(Long resourceId, ResourceDTO resourceDTO);

    /**
     * 删除资源
     */
    void deleteResource(Long resourceId);
}
