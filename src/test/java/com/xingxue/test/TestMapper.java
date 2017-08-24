package com.xingxue.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xingxue.entity.Module;
import com.xingxue.entity.Org;
import com.xingxue.entity.User;
import com.xingxue.mapper.ModuleMapper;
import com.xingxue.mapper.OrgMapper;
import com.xingxue.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestMapper {

	@Autowired
	private ModuleMapper moduleMapper;

	@Autowired
	private OrgMapper orgMapper;
	
	@Autowired
	private UserMapper userMapper;

	@Test
	public void test1() {
		List<Module> list = moduleMapper.findAll();
		for (Module m : list) {
			System.out.println(m);
		}
	}

	@Test
	public void test2() {
		List<Module> list = moduleMapper.findByPid(1);
		for (Module m : list) {
			System.out.println(m);
		}
	}

	@Test
	public void test4() {
		List<Org> list = orgMapper.findByPid(1);
		for (Org m : list) {
			System.out.println(m);
		}
	}
	
	@Test
	public void test5() {
		List<Org> list = orgMapper.findByIds(new Integer[] {1,11,111,112});
		for (Org m : list) {
			System.out.println(m);
		}
	}
	
	@Test
	public void test6() {
		User user = userMapper.findUserWithRoleAndModuleAndOrg("赵总", "123");
		System.out.println(user);
	}

}
