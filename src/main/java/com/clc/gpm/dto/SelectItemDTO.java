/**
 *
 */
package com.clc.gpm.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * SelectItemDTO
 * @author truong.dx
 *
 */
@Getter
@Setter
public class SelectItemDTO implements Comparable<SelectItemDTO> {

    /**
     * code
     */
    private String code;

    /**
     * name
     */
    private String name;

    /**
     * SelectItemDTO
     */
    public SelectItemDTO() {
        super();
    }
    
    

    /**
     * SelectItemDTO
     * @param code String
     */
    public SelectItemDTO(String code) {
        super();
        this.code = code;
    }



    /**
     * SelectItemDTO
     * @param code String
     * @param name String
     */
    public SelectItemDTO(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    @Override
    public int compareTo(SelectItemDTO o) {
        return code.compareTo(o.code);
    }
}
