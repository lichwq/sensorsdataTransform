-- 获取地区

select
    a.id,
    a.areaCn,
    b.city_name_cn,
    b.sheng_name_cn
from
(
select
	get_json_object(json,'$._id') as id,
	get_json_object(json,'$.name') as areaCn,
	get_json_object(json,'$.cityEnglishName') as cityEn
from ods.dim_data
where
     get_json_object(json,'$.type') = 'area2'
) a
left join
(
select
    city_name_en,
    city_name_cn,
    sheng_name_cn
from
    onetruehive.d_01_city
) b
on a.cityEn = b.city_name_en
;

-- 获取城市

select
    get_json_object(json,'$._id') as id,
    get_json_object(json,'$.name') as areaCn,
	get_json_object(json,'$.name') as cityCn,
	get_json_object(json,'$.sheng') as shengCn
from
    ods.dim_data
where
    get_json_object(json,'$.type')  = 'city'
;