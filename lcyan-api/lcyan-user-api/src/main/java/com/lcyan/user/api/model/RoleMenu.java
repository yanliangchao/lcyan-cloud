package com.lcyan.user.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.Table;

import com.lcyan.basic.core.persistence.entity.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "t_role_menu")
@org.hibernate.annotations.Table(appliesTo = "t_role_menu", comment = "用户角色关联")
@IdClass(RoleMenu.class)
@Data
public class RoleMenu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1782979029236838525L;

	@Column(name = "role_id", columnDefinition = "INT(11) UNSIGNED COMMENT '角色ID'", nullable = false)
	@javax.persistence.Id
	private Integer roleId;
	
	@javax.persistence.Id
	@Column(name = "menu_id", columnDefinition = "INT(11) UNSIGNED COMMENT '菜单ID'", nullable = false)
	private Integer menuId;
	
	public static class Id implements Serializable {
		private static final long serialVersionUID = 2751217704686895162L;
		private Integer roleId;
		private Integer menuId;
		
		public Id() {
		}
		public Id(Integer roleId, Integer menuId) {
			super();
			this.roleId = roleId;
			this.menuId = menuId;
		}
		public Integer getRoleId() {
			return roleId;
		}
		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}
		public Integer getMenuId() {
			return menuId;
		}
		public void setMenuId(Integer menuId) {
			this.menuId = menuId;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
			result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Id other = (Id) obj;
			if (menuId == null) {
				if (other.menuId != null)
					return false;
			} else if (!menuId.equals(other.menuId))
				return false;
			if (roleId == null) {
				if (other.roleId != null)
					return false;
			} else if (!roleId.equals(other.roleId))
				return false;
			return true;
		}
	}
}