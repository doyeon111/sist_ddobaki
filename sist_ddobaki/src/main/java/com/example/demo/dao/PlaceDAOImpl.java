package com.example.demo.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import com.example.demo.dto.PlaceSearchCondition;
import com.example.demo.vo.Place;
import com.example.demo.vo.QPlace;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PlaceDAOImpl implements PlaceCustomDAO {

	@Autowired
	JPAQueryFactory factory;

	QPlace p = QPlace.place;

	@Override
	public List<Place> searchTest(PlaceSearchCondition condition) {

		Integer place_region_num = condition.getPlace_region_num();
		Integer place_type_num = condition.getPlace_type_num();
		String place_name = condition.getPlace_name();
		Integer place_park = condition.getPlace_park();
		Integer place_spa = condition.getPlace_spa();
		Integer place_meal = condition.getPlace_spa();
		Integer place_weight = condition.getPlace_weight();
		return factory.selectFrom(p)
				.where(placeRegionNumEquals(place_region_num), placeTypeNumEquals(place_type_num),
						placeNameLike(place_name), placeParkEquals(place_park), placeSpaEquals(place_spa),
						placeMealEquals(place_meal), placeSpaEquals(place_spa), placeWeightEquals(place_weight))
				.fetch();
	}

	private BooleanExpression placeRegionNumEquals(Integer place_region_num) {
		return Objects.isNull(place_region_num) ? null : p.place_region_num.eq(place_region_num);
	}

	private BooleanExpression placeTypeNumEquals(Integer place_type_num) {
		return Objects.isNull(place_type_num) ? null : p.place_type_num.eq(place_type_num);
	}

	private BooleanExpression placeNameLike(String place_name) {
		return StringUtils.isEmpty(place_name) ? null : p.place_name.contains(place_name);
	}

	private BooleanExpression placeParkEquals(Integer place_park) {
		return Objects.isNull(place_park) ? null : p.place_park.eq(place_park);
	}

	private BooleanExpression placeMealEquals(Integer place_meal) {
		return Objects.isNull(place_meal) ? null : p.place_meal.eq(place_meal);
	}

	private BooleanExpression placeSpaEquals(Integer place_spa) {
		return Objects.isNull(place_spa) ? null : p.place_meal.eq(place_spa);
	}

	private BooleanExpression placeWeightEquals(Integer place_weight) {
		return Objects.isNull(place_weight) ? null : p.place_weight.eq(place_weight);
	}
	

	

}