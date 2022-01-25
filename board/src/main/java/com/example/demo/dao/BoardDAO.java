package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.BoardVO;

@Repository
public class BoardDAO {
	
	public List<BoardVO> findAll(){
		return DBManager.findAll();
	}
	
	public int insert(BoardVO b) {
		return DBManager.insert(b);
	}

	public BoardVO findByNo(int no) {
		// TODO Auto-generated method stub
		return DBManager.findByNo(no);
	}

	public int update(BoardVO b) {
		// TODO Auto-generated method stub
		return DBManager.update(b);
	}
	
 
} 
