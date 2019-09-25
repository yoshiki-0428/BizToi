import UsersRepository from "../repositories/UsersRepository";
import AuthRepository from "../repositories/AuthRepository";

const repositories = {
  users: UsersRepository,
  auth: AuthRepository
};

export const RepositoryFactory = {
  get: name => repositories[name]
};