package com.anjowe.behive.repo;

import org.reactivestreams.Publisher;

import com.anjowe.behive.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserRepoImpl implements UserRepo {

	@Override
	public <S extends User> Mono<S> save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> Flux<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> Flux<S> saveAll(Publisher<S> entityStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<User> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<User> findById(Publisher<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Boolean> existsById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Boolean> existsById(Publisher<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<User> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<User> findAllById(Publisher<String> idStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Long> count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(Publisher<String> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> delete(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Iterable<? extends User> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll(Publisher<? extends User> entityStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
