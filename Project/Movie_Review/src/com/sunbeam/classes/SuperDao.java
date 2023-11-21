package com.sunbeam.classes;

import java.sql.Connection;

import com.sunbeam.utilities.DbUtil;

public class SuperDao implements AutoCloseable {
	protected Connection con;

	public SuperDao() {
		con = DbUtil.getConnection();
	}

	@Override
	public void close() throws Exception {
		if (con != null)
			con.close();
	}
}
