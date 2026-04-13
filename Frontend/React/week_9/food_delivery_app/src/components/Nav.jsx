import React from 'react'
import { IoSearch } from 'react-icons/io5'
import { LuShoppingBag } from 'react-icons/lu'
import { MdFastfood } from 'react-icons/md'

function Nav() {
  return (
    <div className='w-full h-25 flex justify-between items-center px-8'>
        <div className='w-15 h-15 bg-white flex justify-center items-center rounded-md shadow-xl'>
            <MdFastfood className='w-8 h-8 text-green-500'/>
        </div>
        <form className='w-[60%] h-15 bg-white flex items-center rounded-2xl shadow-xl px-5 gap-5'>
            <IoSearch className='text-green-500 w-5 h-5'/>
            <input type="text" placeholder='Search items...' className='w-full outline-none text-6'/>
        </form>
        <div className='w-15 h-15 bg-white flex justify-center items-center rounded-md shadow-xl'>
            <LuShoppingBag className='w-8 h-8 text-green-500'/>
        </div>
    </div>
  )
}

export default Nav