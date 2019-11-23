-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 28-Jun-2019 às 21:49
-- Versão do servidor: 5.7.26-0ubuntu0.18.04.1
-- PHP Version: 7.2.19-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_barbearia`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `agenda`
--
CREATE DATABASE db_barbearia;

use db_barbearia;


CREATE TABLE `agenda` (
  `agenda_id` int(11) NOT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `agenda_descricao` varchar(255) DEFAULT NULL,
  `agenda_datahora_inicial` datetime DEFAULT NULL,
  `agenda_datahora_final` datetime DEFAULT NULL,
  `agenda_funcionario` int(11) DEFAULT NULL,
  `funcionario_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `agenda`
--

INSERT INTO `agenda` (`agenda_id`, `cliente_id`, `agenda_descricao`, `agenda_datahora_inicial`, `agenda_datahora_final`, `agenda_funcionario`, `funcionario_id`) VALUES
(1, 1, 'dlkajdlkjadlasjl', '2019-06-27 03:07:08', '2019-06-27 04:11:12', 2, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `categoria_id` int(11) NOT NULL,
  `categoria_nome` varchar(150) NOT NULL,
  `categoria_status` enum('1','0') NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`categoria_id`, `categoria_nome`, `categoria_status`) VALUES
(1, 'Shampo', '1'),
(2, 'Serviço', '1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `cliente_id` int(11) NOT NULL,
  `cliente_nome` varchar(150) NOT NULL,
  `cliente_cpf` varchar(30) DEFAULT NULL,
  `cliente_rg` varchar(30) DEFAULT NULL,
  `cliente_data_nascimento` date DEFAULT NULL,
  `cliente_email` varchar(50) DEFAULT NULL,
  `cliente_endereco` varchar(250) DEFAULT NULL,
  `cliente_telefone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`cliente_id`, `cliente_nome`, `cliente_cpf`, `cliente_rg`, `cliente_data_nascimento`, `cliente_email`, `cliente_endereco`, `cliente_telefone`) VALUES
(1, 'Maycon Henrique matsui', '04537730102', '1829003', '1991-07-02', 'mayconmatsui@gmail.com', 'Rua inirineu bonicontro 473', '(67) 998678256');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `funcionario_id` int(11) NOT NULL,
  `funcionario_nome` varchar(150) CHARACTER SET latin1 NOT NULL,
  `funcionario_tipo` enum('Administrador','Atendente','Barbeiro') CHARACTER SET latin1 NOT NULL,
  `funcionario_cpf` varchar(30) CHARACTER SET latin1 NOT NULL,
  `funcionario_rg` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `funcionario_data_nascimento` date NOT NULL,
  `funcionario_endereco` varchar(250) CHARACTER SET latin1 NOT NULL,
  `funcionario_telefone` varchar(20) CHARACTER SET latin1 NOT NULL,
  `funcionario_email` varchar(50) CHARACTER SET latin1 NOT NULL,
  `funcionario_senha` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`funcionario_id`, `funcionario_nome`, `funcionario_tipo`, `funcionario_cpf`, `funcionario_rg`, `funcionario_data_nascimento`, `funcionario_endereco`, `funcionario_telefone`, `funcionario_email`, `funcionario_senha`) VALUES
(2, 'Maycon H. Masui', 'Administrador', '04537730102', '0980909', '1991-02-07', 'dlkajslkdja slkdjasljdas', '09809808', 'mayconmatsui@gmail.com', '36100AA8BFDB5B125359A696500F874707A9B320C6BA86DCB099A7E6DE35DD66'),
(3, 'Usuario teste', 'Administrador', '75866227010', '98798', '7994-07-26', 'afjkhsfashdfkads', '7575785875', 'teste@teste.com', '46070D4BF934FB0D4B06D9E2C46E346944E322444900A435D7D9A95E6D7435F5');

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_venda`
--

CREATE TABLE `item_venda` (
  `venda_id` int(11) NOT NULL,
  `produto_id` int(11) NOT NULL,
  `item_quantidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `produto_id` int(11) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `produto_nome` varchar(100) NOT NULL,
  `produto_descricao` varchar(255) DEFAULT NULL,
  `produto_valor_custo` double(10,2) DEFAULT NULL,
  `produto_valor_venda` double(10,2) NOT NULL,
  `produto_quantidade` int(11) NOT NULL,
  `produto_tipo` enum('0','1') NOT NULL DEFAULT '0' COMMENT '0 Produto 1 serviço',
  `produto_status` enum('1','0') NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`produto_id`, `categoria_id`, `produto_nome`, `produto_descricao`, `produto_valor_custo`, `produto_valor_venda`, `produto_quantidade`, `produto_tipo`, `produto_status`) VALUES
(1, 1, 'Shampo anti-caspas', 'Shampoo para caspas ranca tudo', 64.90, 99.80, 100, '0', '1'),
(2, 2, 'Corte Cabelo', 'Serviço de corte de Cabelos', 10.90, 40.50, 0, '0', '1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE `venda` (
  `venda_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `venda_data` date NOT NULL,
  `venda_total` double(10,2) NOT NULL,
  `funcionario_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`agenda_id`),
  ADD KEY `pk_cliente_agenda` (`cliente_id`),
  ADD KEY `pk_funcionrio_agenda` (`agenda_funcionario`),
  ADD KEY `pk_atendente_agenda` (`funcionario_id`);

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`categoria_id`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cliente_id`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`funcionario_id`);

--
-- Indexes for table `item_venda`
--
ALTER TABLE `item_venda`
  ADD KEY `pk_item_venda` (`venda_id`),
  ADD KEY `pk_produto_venda` (`produto_id`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`produto_id`),
  ADD KEY `pk_produto_categoria` (`categoria_id`);

--
-- Indexes for table `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`venda_id`),
  ADD KEY `pk_cliente_venda` (`cliente_id`),
  ADD KEY `pk_funcionario_venda` (`funcionario_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agenda`
--
ALTER TABLE `agenda`
  MODIFY `agenda_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `categoria_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cliente_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `funcionario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `produto_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `venda`
--
ALTER TABLE `venda`
  MODIFY `venda_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `pk_atendente_agenda` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`funcionario_id`),
  ADD CONSTRAINT `pk_cliente_agenda` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`cliente_id`),
  ADD CONSTRAINT `pk_funcionrio_agenda` FOREIGN KEY (`agenda_funcionario`) REFERENCES `funcionario` (`funcionario_id`);

--
-- Limitadores para a tabela `item_venda`
--
ALTER TABLE `item_venda`
  ADD CONSTRAINT `pk_item_venda` FOREIGN KEY (`venda_id`) REFERENCES `venda` (`venda_id`),
  ADD CONSTRAINT `pk_produto_venda` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`produto_id`);

--
-- Limitadores para a tabela `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `pk_produto_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`categoria_id`);

--
-- Limitadores para a tabela `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `pk_cliente_venda` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`cliente_id`),
  ADD CONSTRAINT `pk_funcionario_venda` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`funcionario_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
