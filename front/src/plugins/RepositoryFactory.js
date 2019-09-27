import UsersRepository from "../repositories/UsersRepository";
import AuthRepository from "../repositories/AuthRepository";
import GoogleBookRepository from "../repositories/GoogleBookRepository";

const repositories = {
  googleBookApi: GoogleBookRepository,
  users: UsersRepository,
  auth: AuthRepository
};

export const RepositoryFactory = {
  get: name => repositories[name]
};