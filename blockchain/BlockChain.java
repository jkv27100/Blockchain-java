package com.blockchain;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;



 class Block {
	 SHA256 sha256 = new SHA256();
	 
	 //Block content
	 
	 private String index;
	 private String previousHash;
	 private String blockData;
	 private String blockHash;
	
	public void setIndex(String index) {
		this.index = index;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public void setBlockData(String blockData) {
		this.blockData = blockData;
	}

	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}

	@Override
	public String toString() {
		return "[index=" + index + ", previousHash=" + previousHash + ", blockData=" + blockData + ", blockHash="
				+ blockHash + "]";
	}

	public String getIndex() {
		return index;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public String getBlockData() {
		return blockData;
	}

	public String getBlockHash() {
		return blockHash;
	}

	//constructor for genisis block
	
	public Block(String index,String previousHash, String blockData) throws NoSuchAlgorithmException {
		
		this.index = index;
		this.previousHash = previousHash;
		this.blockData = blockData;
		this.blockHash = this.clalculateHash();
		
	}
	
	//constructor for adding new block
	
	public Block(String index, String blockData) {
		
		this.index = index;
		this.blockData = blockData;
		this.previousHash = this.getPreviousHash();
		this.blockHash = this.getBlockHash();
	}

	//calculating hash
	
	public String clalculateHash() throws NoSuchAlgorithmException {
		return sha256.toHexString(sha256.getSHA(this.index))+sha256.toHexString(sha256.getSHA(this.previousHash))+sha256.toHexString(sha256.getSHA(this.blockData));
		

	}
	
	
 }


public class BlockChain {

	ArrayList<Block>  chain= new ArrayList<>();
	 public BlockChain() throws NoSuchAlgorithmException {
		 chain.add(this.createGenisBlock()); //creating genisis block
		 
		 	
	}
	 public Block createGenisBlock() throws NoSuchAlgorithmException {
		 return new Block("0","0","first block");
	 }
	
	public Block getLatestBlock() {
		return this.chain.get(this.chain.size()-1);
		
		
	}
	public void addBlock(Block newBlock) throws NoSuchAlgorithmException {
		newBlock.setPreviousHash(this.getLatestBlock().getBlockHash());
		newBlock.setBlockHash(newBlock.clalculateHash());
		chain.add(newBlock);
		
		
	}

}
