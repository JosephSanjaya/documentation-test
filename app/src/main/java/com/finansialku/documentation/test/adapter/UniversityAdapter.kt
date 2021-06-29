/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.finansialku.documentation.test.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.finansialku.documentation.core.domain.University
import com.finansialku.documentation.test.R
import com.finansialku.documentation.test.databinding.RowUniversitasBinding

class UniversityAdapter(data: MutableList<University?>) :
    BaseQuickAdapter<University?, BaseDataBindingHolder<RowUniversitasBinding>>(
        R.layout.row_universitas,
        data
    ) {
    override fun convert(holder: BaseDataBindingHolder<RowUniversitasBinding>, item: University?) {
        holder.dataBinding?.tvUniversitas?.text = item?.name
    }
}
