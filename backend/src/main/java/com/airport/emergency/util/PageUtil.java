package com.airport.emergency.util;

import com.airport.emergency.exception.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 分页工具类
 */
public class PageUtil {
    
    /**
     * 创建Pageable对象
     */
    public static Pageable createPageable(Integer pageNumber, Integer pageSize) {
        int page = pageNumber != null ? pageNumber : Constants.DEFAULT_PAGE_NUMBER;
        int size = pageSize != null ? pageSize : Constants.DEFAULT_PAGE_SIZE;
        
        if (page < 1) {
            throw new BadRequestException("pageNumber", "must be greater than 0");
        }
        if (size < 1 || size > Constants.MAX_PAGE_SIZE) {
            throw new BadRequestException("pageSize", 
                    "must be between 1 and " + Constants.MAX_PAGE_SIZE);
        }
        
        return PageRequest.of(page - 1, size);
    }
    
    /**
     * 创建Pageable对象（带排序）
     */
    public static Pageable createPageable(Integer pageNumber, Integer pageSize, 
                                         String sortBy, String sortDirection) {
        Pageable pageable = createPageable(pageNumber, pageSize);
        
        if (sortBy != null && !sortBy.isEmpty()) {
            Sort.Direction direction = "desc".equalsIgnoreCase(sortDirection) ? 
                    Sort.Direction.DESC : Sort.Direction.ASC;
            Sort sort = Sort.by(direction, sortBy);
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        
        return pageable;
    }
}
