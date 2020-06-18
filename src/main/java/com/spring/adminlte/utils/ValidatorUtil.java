/*-----------------------------------------------------------------------------------------
 * NAME : ValidatorUtil.java
 * VER  : v0.1
 * PROJ : ppcb-apsara
 * Copyright 2018 PPCB All rights reserved
 *-----------------------------------------------------------------------------------------
 *                      H      I      S      T      O      R      Y
 *-----------------------------------------------------------------------------------------
 *   DATE        AUTHOR         DESCRIPTION                        
 * ----------  --------------  ------------------------------------------------------------
 * 2018-08-07   phorly          creation
 *---------------------------------------------------------------------------------------*/
package com.spring.adminlte.utils;

import com.spring.adminlte.mmap.MMap;

/**
 * <PRE>
 *  -- detail description --
 * </PRE>
 *
 * @logicalName ValidatorUtil
 * @version   0.1, 2018-08-07
 */
public class ValidatorUtil {
	public static void validate(MMap ipParam, String... ipFields ) throws Exception {
		for ( String sKey : ipFields ) {
			if ( ipParam.getString(sKey).trim().equals("") ) {
				throw new Exception("Required information is missing :"+sKey);
			}
		}
	}
	
}
