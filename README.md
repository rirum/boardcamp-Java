# 🎲🗺️ Boardcamp 🗺️🎲
Lembra que antigamente tínhamos as locadoras de VHS? Não tínhamos catálogo da Netflix, aí quando queríamos assistir um filme locávamos um título na locadora mais próxima. Hoje, com as comodidades da vida moderna não precisamos mais nos deslocar pra locar um filme porém tem um tipo de locadora que está ganhando espaço no mercado: a de **jogos de tabuleiro**. Hoje quase ninguém tem mais espaço para manter uma biblioteca de jogos de tabuleiro em casa: a solução pra jogar diferentes títulos fica em locar um jogo praquela reunião de amigos ou família. Essa é uma API REST que desenvolvi para atender uma locadora dessas!


## Deploy
https://boardcampjava-api.onrender.com/


## Como funciona?
Temos 3 entidades nessa API: `customers`, `games` e `rentals`.

### Para a entidade `games` temos:
- GET /games: listamos todos os jogos que estão da database. Modelo de resposta da rota:
```
[
  {
    id: 1,
    name: 'Banco Imobiliário',
    image: 'http://',
    stockTotal: 3,
    pricePerDay: 1500
  },
  {
    id: 2,
    name: 'Detetive',
    image: 'http://',
    stockTotal: 1,
    pricePerDay: 2500
  },
]
```
  
- POST /games: insere um jogo na database.
  Request do body deve ser o seguinte:
```
{
  name: 'Banco Imobiliário',
  image: 'http://www.imagem.com.br/banco_imobiliario.jpg',
  stockTotal: 3,
  pricePerDay: 1500
}
```
**Cases de erro:**
- `name` deve estar presente e não pode ser nulo nem uma string vazia - (retorna erro 400 BAD REQUEST caso seja nulo ou string vazia)
- `stockTotal` e `pricePerDay` devem ser números maiores que 0, não podem ser nulos - (retorna erro 400 BAD REQUEST caso seja 0 ou menor que 0)
- `name` não pode ser um nome de jogo já existente ⇒ caso haja um jogo já registrado com o mesmo nome (retorna erro 409 CONFLICT)


  
### Para a entidade `customers` temos:
- GET /customers/:id : procura um cliente pelo seu id.
### Case de erro:
Se o cliente com id dado não existir responde com status retorna o erro 404 (NOT FOUND).

-POST /customers : cadastra um cliente
Request do body deve ser o seguinte:
```
{
  name: 'João Alfredo',
  cpf: '01234567890'
}
```
**Cases de erro**:
- `cpf` deve ser uma string com 11 caracteres, não pode ser nulo nem string vazia - (retorna erro 400 BAD REQUEST caso seja nulo ou string vazia)
- `name` deve estar presente e não pode ser nulo nem string vazia - (retorna erro 400 BAD REQUEST caso seja nulo ou string vazia)
- `cpf` não pode ser de um cliente já existente ⇒ (retorna erro 409 CONFLICT caso já exista)



### Para a entidade `rentals` temos:
- GET /rentals : lista os aluguéis na database contendo o customer e o game do aluguel em questão em cada aluguel, e status 200 (OK).
- POST /rentals : cria o aluguel. Formato do body da requisição abaixo:
  
```
{
  customerId: 1,
  gameId: 1,
  daysRented: 3
}
```

- PUT /rentals/:id/return : retornando o aluguel finalizado completo (com id, customer e game).
Formato da resposta deve ser conforme abaixo:

```
{
    id: 1,
    rentDate: '2021-06-20',
    daysRented: 3,
    returnDate: '2021-06-25', 
    originalPrice: 4500,
    delayFee: 3000, 
    customer: {
      id: 1,
      name: 'João Alfredo',
		  cpf: '01234567890'
    },
    game: {
      id: 1,
		  name: 'Banco Imobiliário',
		  image: 'http://www.imagem.com.br/banco.jpg',
		  stockTotal: 3,
		  pricePerDay: 1500
    }
  }
```

**Cases de erro:**
- Deve verificar se o `id` do aluguel fornecido existe. Se não, deve responder com status `404 (NOT FOUND)`.
- Se o aluguel já está finalizado deve responder com status 422 (UNPROCESSABLE ENTITY).



## Motivação 
Este projeto foi feito para praticar a construção de uma API REST utilizando Java com Springboot

## Tecnologias utilizadas
Para este projeto, foram utilizadas:

- Java
- SpringBoot;
- PostgreSQL para o banco de dados;


## Como rodar o projeto
Para executar este projeto em desenvolvimento, é necessário seguir os passos abaixo:

- Clonar o repositório;
- Compile o projeto `mvn clean install`
- Em seguida, criar o arquivo .env com base no arquivo .env.example
- Execute ApiApplication.java (localizado em src/main/java/com/boardcamp/demo/) com jdk 17
