package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class ProductDAO {
	//Constructor
	public ProductDAO() {
	}
	
	//Method
	//��ǰ����� ���� DBMS�� ����
	public void insertProduct(ProductVO productVO) throws SQLException {
		System.out.println("<<<<< ProductDAO : insertProduct() ���� >>>>>");
		System.out.println("���� productVO : " + productVO);
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO product VALUES (seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, productVO.getProdName());
		pStmt.setString(2, productVO.getProdDetail());
		pStmt.setString(3, productVO.getManuDate());
		pStmt.setInt(4, productVO.getPrice());
		pStmt.setString(5, productVO.getFileName());
		pStmt.executeUpdate();
		System.out.println("insert �Ϸ� : " + sql);
		
		pStmt.close();
		con.close();	
		System.out.println("<<<<< ProductDAO : insertProduct() ���� >>>>>");
	}
	
	
	//��ǰ���� ��ȸ�� ���� DBMS�� ����
	public ProductVO findProduct(int prodNo) throws Exception {
		System.out.println("<<<<< ProductDAO : findProduct() ���� >>>>>");
		System.out.println("���� prodNo : " + prodNo);
		
		Connection con = DBUtil.getConnection();

		String sql = "SELECT * FROM product WHERE prod_no=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, prodNo);
		ResultSet rs = pStmt.executeQuery();
		System.out.println("sql ���ۿϷ� : " + sql);

		ProductVO productVO = new ProductVO();
		while (rs.next()) {
			productVO.setProdNo(rs.getInt("prod_no"));
			productVO.setProdName(rs.getString("prod_name"));
			productVO.setProdDetail(rs.getString("prod_detail"));
			productVO.setManuDate(rs.getString("manufacture_day"));
			productVO.setPrice(rs.getInt("price"));
			productVO.setFileName(rs.getString("image_file"));
			productVO.setRegDate(rs.getDate("reg_date"));
		}
		System.out.println("productVO ���ÿϷ� : " + productVO);
		
		rs.close();
		pStmt.close();
		con.close();
		System.out.println("<<<<< ProductDAO : findProduct() ���� >>>>>");
		return productVO;
	}
	
	
	//��ǰ��� ��ȸ�� ���� DBMS�� ����
	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		System.out.println("<<<<< ProductDAO : getProductList() ���� >>>>>");
		System.out.println("���� searchVO : " + searchVO);
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM product ";
		
		//SearchCondition�� ���� ���� ���
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " WHERE prod_no LIKE '%" + searchVO.getSearchKeyword() + "%'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " WHERE prod_name LIKE '%" + searchVO.getSearchKeyword() + "%'";
			} else if (searchVO.getSearchCondition().equals("2")) {
				sql += " WHERE price LIKE '%" + searchVO.getSearchKeyword() + "%'";
			}
		}
		sql += " ORDER BY prod_no";
		
		PreparedStatement pStmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
														    ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = pStmt.executeQuery();
		System.out.println("sql ���ۿϷ� : " + sql);
		
		rs.last(); //boolean last() : ������ ������ Ŀ�� �̵�
		int total = rs.getRow(); //int getRow() : ���� ���ȣ �˻� (������ ���ȣ = ��ü ���� ��)
		System.out.println("��ü �ο� ��(total) : " + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));
		System.out.println("map�� count �߰� : " + map);

		//boolean absolute(int row) : ������ ���ȣ�� Ŀ�� �̵�
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		PurchaseService service = new PurchaseServiceImpl();
		if (total > 0) {
			for (int i=0; i<searchVO.getPageUnit(); i++) {
				ProductVO productVO = new ProductVO();
				productVO.setProdNo(rs.getInt("prod_no"));
				productVO.setProdName(rs.getString("prod_name"));
				productVO.setProdDetail(rs.getString("prod_detail"));
				productVO.setManuDate(rs.getString("manufacture_day"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setFileName(rs.getString("image_file"));
				productVO.setRegDate(rs.getDate("reg_date"));
				
				if(service.getPurchase2(productVO.getProdNo()) != null) {
					productVO.setProTranCode("������"); 
				}else {
					productVO.setProTranCode("�Ǹ���");	
				}
				
				list.add(productVO);
				if (!rs.next()) {
					break;
				}
				System.out.println("productVO ���ÿϷ� : " + productVO);	
			}
		}
		map.put("list", list);
		System.out.println("map�� list �߰� : " + map);
		System.out.println("list.size() : " + list.size()); 
		System.out.println("map.size() : " + map.size()); 
		
		rs.close();
		pStmt.close();
		con.close();
		System.out.println("<<<<< ProductDAO : getProductList() ���� >>>>>");
		return map;
	}
	
	
	//��ǰ���� ������ ���� DBMS�� ����
	public void updateProduct(ProductVO productVO) throws Exception {
		System.out.println("<<<<< ProductDAO : updateProduct() ���� >>>>>");
		System.out.println("���� productVO : " + productVO);
		
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE product "
				    + "SET prod_name=?, prod_detail=?, manufacture_day=?, "
				    + "price=?, image_file=? WHERE prod_no=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, productVO.getProdName());
		pStmt.setString(2, productVO.getProdDetail());
		pStmt.setString(3, productVO.getManuDate());
		pStmt.setInt(4, productVO.getPrice());
		pStmt.setString(5, productVO.getFileName());
		pStmt.setInt(6, productVO.getProdNo());
		pStmt.executeUpdate();
		System.out.println("update �Ϸ� : " + sql);
		
		pStmt.close();
		con.close();
		System.out.println("<<<<< ProductDAO : updateProduct() ���� >>>>>");
	}

}//end of class
